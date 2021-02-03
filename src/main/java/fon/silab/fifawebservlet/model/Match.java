/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Veljko
 */
@Entity(name = "Match_table")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @JoinColumn(name = "Match_date")
    private Date date;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "Match_host")
    private Selection host;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "Match_away")
    private Selection away;

    private int hostGoals;
    private int awayGoals;

    @ManyToOne()
    private MatchType matchType;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "UserID")
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Selection getHost() {
        return host;
    }

    public void setHost(Selection host) {
        this.host = host;
    }

    public Selection getAway() {
        return away;
    }

    public void setAway(Selection away) {
        this.away = away;
    }

    public int getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(int hostGoals) {
        this.hostGoals = hostGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", date=" + date + ", host=" + host + ", away=" + away + ", hostGoals=" + hostGoals + ", awayGoals=" + awayGoals + ", matchType=" + matchType + ", user=" + user + '}';
    }

}
