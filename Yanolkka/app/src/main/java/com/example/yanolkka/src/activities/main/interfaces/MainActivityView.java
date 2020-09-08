package com.example.yanolkka.src.activities.main.interfaces;

import com.example.yanolkka.src.activities.main.models.Group;

import java.util.ArrayList;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getGroups(ArrayList<Group> gList);
}
