/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package android.irail.be.hyperrail.irail.implementation;

import java.io.Serializable;
import java.util.Date;

/**
 * A disturbance on the rail network
 */
public class Disturbance implements Serializable {
    private final String link;
    private final Date timestamp;
    private final String title;
    private final String description;
    private final int id;


    public Disturbance(int id, Date timestamp, String title, String description, String link) {
        this.id = id;
        this.timestamp = timestamp;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Date getTime() {
        return timestamp;
    }
}
