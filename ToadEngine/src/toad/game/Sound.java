package toad.game;

import org.lwjgl.openal.AL10;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Objects;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.libc.LibCStdlib.free;

public class Sound {

    private int bufferId;
    private int sourceId;

    private boolean playing = false;

    public Sound(String audioPath, boolean loops) {
        File file = new File(audioPath);
        String filepath = file.getAbsolutePath();

        // Reserving memory for audio info
        stackPush();
        IntBuffer channelsBuffer = stackMallocInt(1);
        stackPush();
        IntBuffer sampleRateBuffer = stackMallocInt(1);

        // Apparently somethin complex goin on
        ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(filepath, channelsBuffer, sampleRateBuffer);

        if (rawAudioBuffer == null) { // Sound must be .ogg format
            System.out.println("Could not load sound " + filepath);
            stackPop(); // free memory
            stackPop();
            return;
        }

        // get information from stb method
        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();

        // Free
        stackPop();
        stackPop();

        int format = -1;
        if (channels == 1) {
            format = AL_FORMAT_MONO16;
        } else if (channels == 2) {
            format = AL_FORMAT_STEREO16;
        }

        bufferId = alGenBuffers();
        alBufferData(bufferId, format, rawAudioBuffer, sampleRate);

        sourceId = alGenSources();

        alSourcei(sourceId, AL_BUFFER, bufferId);
        alSourcei(sourceId, AL_LOOPING, loops ? 1 : 0);
        alSourcei(sourceId, AL_POSITION, 0);
        alSourcef(sourceId, AL_GAIN, 0.3f);

        // free audiobuffer
        free(rawAudioBuffer);
    }

    // Delete old sounds when switching scenes, levels, etc. (do we need this?)
    public void delete() {
        alDeleteSources(sourceId);
        alDeleteBuffers(bufferId);
    }

    public void play() {
        int state = alGetSourcei(sourceId, AL_SOURCE_STATE);

        if (state == AL_STOPPED) {
            playing = false;
            alSourcei(sourceId, AL_POSITION, 1);
        }

        if (!playing) {
            alSourcePlay(sourceId);
            playing = true;
        }
    }

    public void stop() {
        if (playing) {
            alSourceStop(sourceId);
            playing = false;
        }
    }

    public boolean isPlaying() {
        int state = alGetSourcei(sourceId, AL_SOURCE_STATE);
        if (state == AL_STOPPED) {
            playing = false;
        }
        return playing;
    }

    public void setSourcePos(float x, float y, float gain) {
        alSource3f(sourceId, AL_POSITION, x, y, 0);
        alSourcef(sourceId, AL_GAIN, gain);
    }
}
