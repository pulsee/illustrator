package com.kuks.illustrator;

public class Event<T> {

    private T mContent;

    private boolean hasBeenHandled = false;

    public Event(T content) {
        if (content == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        mContent = content;
    }

    public T getContentIfNotHandled() {
        return mContent;
    }

}