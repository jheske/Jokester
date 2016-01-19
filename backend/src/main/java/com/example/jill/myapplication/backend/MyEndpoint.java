/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jill.myapplication.backend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.example.JokeTeller;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.jill.example.com",
                ownerName = "backend.myapplication.jill.example.com",
                packagePath = ""
        )
)

public class MyEndpoint {
    JokeTeller jokeTeller = new JokeTeller();
    /**
     * Endpoint method that returns a joke
     **/
    @ApiMethod(name = "getGoodJoke", path="goodjoke")
    public MyJoke getGoodJoke() {
        MyJoke response = new MyJoke();
        String joke = jokeTeller.getJoke(Jokes.JOKE_TYPE.GOOD);
        response.setData(joke);
        return response;
    }

    /** Endpoint method that returns a bad joke **/
     @ApiMethod(name="getBadJoke", path="badjoke")
     public MyJoke getBadJoke() {
         MyJoke response = new MyJoke();
        String joke = jokeTeller.getJoke(Jokes.JOKE_TYPE.BAD);
        response.setData(joke);
        return response;
     }
}
