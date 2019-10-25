package com.jt.springbootlearn.bean.disc;

import java.util.List;

public class BlankDisc implements CompactDisc {

    public BlankDisc(){}

    private String title;
    private String artist;
    private List<String> tracks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void playTrack(int trackNumber) throws IllegalArgumentException {
        if(trackNumber >= tracks.size() || trackNumber < 0){
            throw new IllegalArgumentException("Array out of bounds");
        }
        String track = tracks.get(trackNumber);
        System.out.println("play: "+ track);
    }
}
