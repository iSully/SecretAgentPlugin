package com.iSully.TagGame;

public class PartyGame {
    public String partyCode;
    public String gameWorld;
    public String partyHost;
    public String[] safePlayers;
    public String[] taggedPlayers;
    public String status;
    public GamePlayer gamePlayer;
    
    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    public String getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(String gameWorld) {
        this.gameWorld = gameWorld;
    }

    public String getPartyHost() {
        return partyHost;
    }

    public void setPartyHost(String partyHost) {
        this.partyHost = partyHost;
    }

    public String[] getSafePlayers() {
        return safePlayers;
    }

    public void setSafePlayers(String[] safePlayers) {
        this.safePlayers = safePlayers;
    }

    public String[] getTaggedPlayers() {
        return taggedPlayers;
    }

    public void setTaggedPlayers(String[] taggedPlayers) {
        this.taggedPlayers = taggedPlayers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}
