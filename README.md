# GPT4J
A Java wrapper for Open AI's GPT-3 models.
Built using curl commands to make the requests.

## Quickstart
```lang-Java
import com.gpt4j.request.GptRequest;
import com.gpt4j.output.GptOutput;
import com.gpt4j.GPTRequestHandler;

public class Main {

    private static String API_KEY = "Your API key.";

    public static void main(String[] args) throws Exception {
        GPTRequestHandler gpt = new GPTRequestHandler(API_KEY);
        GptRequest req = new GptRequest();
        req.setPrompt("Hello world!");
        GptOutput out = gpt.generate(req, GPTRequestHandler.Engine.ADA);
        System.out.println(out.getChoices().get(0).getText());

    }
}
```

Output:
```
A new game called 'Laserjunk' is available for download. It's a pretty cool game, but I wonder if I'll ever play it. I'm also making a Movie of it, and it will be available on my channel later tonight.
```


## About
This repository is a Java Maven project.

### Library
`/lib` directory

### Source code
`/src` directory


## Documentation
As of yet. There is currently no documentation. I do hope to release some in the near future.
The source code is pretty easy to pull apart, to taking a look at the source code should give you all the information you need. 
