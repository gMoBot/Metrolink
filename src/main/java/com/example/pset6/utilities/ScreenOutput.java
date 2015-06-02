package com.example.pset6.utilities;

import org.springframework.stereotype.Service;

/**
 * Created by garrettcoggon on 5/28/15.
 */
@Service
public class ScreenOutput implements AppOutput {

    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
