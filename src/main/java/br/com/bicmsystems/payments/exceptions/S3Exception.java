package br.com.bicmsystems.payments.exceptions;

import java.text.MessageFormat;

public class S3Exception extends Exception {

    public S3Exception(String bucket, String key, Exception ex) {
        super(MessageFormat.format("Erro ao executar serviço S3: Bucket {0} - Key {1}", bucket, key), ex);
    }

}
