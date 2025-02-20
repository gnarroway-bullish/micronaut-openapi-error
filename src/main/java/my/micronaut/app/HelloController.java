package my.micronaut.app;

import io.micronaut.http.annotation.*;

@Controller("/hello")
public class HelloController {

    @Get("/")
    public COSEAlgorithmIdentifier index() {
        return COSEAlgorithmIdentifier.EdDSA;
    }
}