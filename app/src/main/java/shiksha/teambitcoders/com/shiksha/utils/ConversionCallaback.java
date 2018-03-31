package shiksha.teambitcoders.com.shiksha.utils;

/**
 * Created by Subham Lenka on 25-08-2017.
 */
public interface ConversionCallaback {

    public void onSuccess(String result);

    public void onCompletion();

    public void onErrorOccured(String errorMessage);

}