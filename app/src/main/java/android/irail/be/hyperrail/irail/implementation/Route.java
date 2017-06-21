/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package android.irail.be.hyperrail.irail.implementation;

import android.irail.be.hyperrail.irail.db.Station;

import java.io.Serializable;
import java.util.Date;

/**
 * A route between 2 stations, which might consist of multiple vehicles with transfers in between
 */
public class Route implements Serializable {


    private final Station departureStation;
    private final Station arrivalStation;
    private final Date departureTime;
    private final Date arrivalTime;

    private final int departureDelay;
    private final int arrivalDelay;

    private final String departurePlatform;
    private final boolean isDeparturePlatformNormal;
    private final String arrivalPlatform;
    private final boolean isArrivalDeparturePlatformNormal;

    private final TrainStub[] trains;
    private final Transfer[] transfers;

    Route(Station departureStation, Station arrivalStation, Date departureTime, int departureDelay, String departurePlatform, boolean isDeparturePlatformNormal, Date arrivalTime, int arrivalDelay, String arrivalPlatform, boolean isArrivalDeparturePlatformNormal, TrainStub[] trains, Transfer[] transfers) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;

        this.departureTime = departureTime;
        this.departureDelay = departureDelay;
        this.isDeparturePlatformNormal = isDeparturePlatformNormal;
        this.arrivalTime = arrivalTime;
        this.arrivalDelay = arrivalDelay;

        this.departurePlatform = departurePlatform;
        this.arrivalPlatform = arrivalPlatform;

        this.isArrivalDeparturePlatformNormal = isArrivalDeparturePlatformNormal;
        this.trains = trains;
        this.transfers = transfers;
    }

    public long getDuration() {
        return getArrivalTime().getTime() - getDepartureTime().getTime();
    }


    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Transfer getOrigin() {
        return transfers[0];
    }

    public Transfer getDestination() {
        return transfers[transfers.length - 1];
    }

    public int getTransferCount() {
        // minus origin and destination
        return transfers.length - 2;
    }

    public int getStationCount() {
        return transfers.length;
    }

    public TrainStub[] getTrains() {
        return trains;
    }

    public Transfer[] getTransfers() {
        return transfers;
    }

    public int getArrivalDelay() {
        return arrivalDelay;
    }

    public int getDepartureDelay() {
        return departureDelay;
    }

    public String getDeparturePlatform() {
        return departurePlatform;
    }

    public String getArrivalPlatform() {
        return arrivalPlatform;
    }

    public boolean isArrivalDeparturePlatformNormal() {
        return isArrivalDeparturePlatformNormal;
    }

    public boolean isDeparturePlatformNormal() {
        return isDeparturePlatformNormal;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }
}
