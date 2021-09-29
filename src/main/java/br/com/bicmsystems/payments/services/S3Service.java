package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.exceptions.S3Exception;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    public PutObjectResult saveObject(String bucket, String key, String content) throws S3Exception {

        try {
            if (Objects.isNull(bucket) || Objects.isNull(key) || Objects.isNull(content)) {
                throw new S3Exception(bucket, key, new Exception("Os campos não devem ser nulos"));
            }
            return amazonS3.putObject(bucket, key, content);
        } catch (Exception ex) {
            throw new S3Exception(bucket, key, ex);
        }

    }

}
