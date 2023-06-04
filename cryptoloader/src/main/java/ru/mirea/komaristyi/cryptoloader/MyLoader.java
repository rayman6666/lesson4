package ru.mirea.komaristyi.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    public static final String ARG_WORD = "word";
    private String message;

    public MyLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        if (bundle != null) {
            byte[] encrypted_msg = bundle.getByteArray(ARG_WORD);
            byte[] key = bundle.getByteArray("key");
            SecretKey original_key = new SecretKeySpec(key, 0, key.length, "AES");
            message = decryptMsg(encrypted_msg, original_key);

        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        SystemClock.sleep(1000);
        return message;
    }

    public static String decryptMsg(byte[] encrypted_msg, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(encrypted_msg));

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
