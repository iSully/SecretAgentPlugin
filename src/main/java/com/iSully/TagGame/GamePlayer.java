package com.iSully.TagGame;

public class GamePlayer {
    private String username;
    private String activeWorld;
    private String currentGameMode;
    private String partyCode;
    private String status;
    private String activeTile;

    // Constructor
    public GamePlayer(String username, String activeWorld) {
        this.username = username;
        this.activeWorld = activeWorld;
        this.currentGameMode = "Party";
        this.partyCode = "";
        this.status = "Not It";
        this.activeTile = "";
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActiveWorld() {
        return activeWorld;
    }

    public void setActiveWorld(String activeWorld) {
        this.activeWorld = activeWorld;
    }

    public String getCurrentGameMode() {
        return currentGameMode;
    }

    public void setCurrentGameMode(String currentGameMode) {
        this.currentGameMode = currentGameMode;
    }

    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActiveTile() {
        return activeTile;
    }

    public void setActiveTile(String activeTile) {
        this.activeTile = activeTile;
    }
}
