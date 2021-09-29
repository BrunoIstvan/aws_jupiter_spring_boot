package br.com.bicmsystems.payments.services;

import br.com.bicmsystems.payments.exceptions.S3Exception;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

class S3ServiceTest {

    private S3Service s3Service;
    private AmazonS3 amazonS3;

    @BeforeEach
    public void setUp() {
        amazonS3 = Mockito.mock(AmazonS3.class);
        s3Service = new S3Service(amazonS3);
    }

    @Test
    public void testS3PutObject() throws S3Exception {

        String bucket = "bucket", file = "file", content = "";
        var result = new PutObjectResult();
        Mockito.doReturn(result).when(amazonS3).putObject(bucket, file, content);
        s3Service.saveObject(bucket, file, content);
        Mockito.verify(amazonS3, Mockito.times(1)).putObject(bucket, file, content);

    }


    @Test
    public void testS3PutObjectException() throws S3Exception {

        String bucket = "bucket", file = "file", content = "";
        var result = new PutObjectResult();

        S3Exception nullException = Assertions.assertThrows(S3Exception.class, () -> {
            s3Service.saveObject(null, null, null);
        });

        Assertions.assertEquals("Erro ao executar serviço S3: Bucket null - Key null",
                nullException.getMessage());

        BDDMockito.given(amazonS3.putObject(bucket, file, content)).willAnswer(
                invocation -> {throw new Exception();});

        S3Exception s3Exception = Assertions.assertThrows(S3Exception.class, () -> {
            s3Service.saveObject(bucket, file, content);
        });

        Assertions.assertEquals("Erro ao executar serviço S3: Bucket bucket - Key file",
                s3Exception.getMessage());

    }

}