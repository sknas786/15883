package shiksha.teambitcoders.com.shiksha;

import shiksha.teambitcoders.com.shiksha.translators.IConvertor;
import shiksha.teambitcoders.com.shiksha.translators.SpeechToTextConvertor;
import shiksha.teambitcoders.com.shiksha.translators.TextToSpechConvertor;
import shiksha.teambitcoders.com.shiksha.utils.ConversionCallaback;

/**
 * Created by Subham Lenka on 25-08-2017.
 */

public class TranslatorFactory {
    private static TranslatorFactory ourInstance = new TranslatorFactory();

    public enum TRANSLATOR_TYPE {TEXT_TO_SPEECH, SPEECH_TO_TEXT}

    private TranslatorFactory() {
    }

    public static TranslatorFactory getInstance() {
        return ourInstance;
    }

    /**
     * Factory method to return object of STT or TTS
     *
     * @param translator_type
     * @param conversionCallaback
     * @return
     */
    public IConvertor getTranslator(TRANSLATOR_TYPE translator_type, ConversionCallaback conversionCallaback) {
        switch (translator_type) {
            case TEXT_TO_SPEECH:

                //Get Text to speech translator
                return new TextToSpechConvertor(conversionCallaback);

            case SPEECH_TO_TEXT:

                //Get speech to text translator
                return new SpeechToTextConvertor(conversionCallaback);
        }

        return null;
    }
}
