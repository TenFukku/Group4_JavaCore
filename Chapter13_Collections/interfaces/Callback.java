package interfaces;

public interface Callback {
    void onSuccess(String message);
    void onError(String error);
    void onProgress(int percentage);
} 