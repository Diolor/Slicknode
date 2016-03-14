/*
 * Copyright 2016 Dionysis Lorentzos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lorentzos.slicknode.data

import android.net.Uri
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.DataApi
import com.google.android.gms.wearable.DataItemBuffer
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable.DataApi
import com.lorentzos.slicknode.internal.toObservable
import rx.Observable

/**
 * Contains getting and putting actions for the [DataItem] class
 */
class DataItemObservable {

  companion object {

    /**
     * Adds a [DataItem] to the Android Wear network. The updated item is synchronized across all devices.
     */
    fun putItem(googleApiClient: GoogleApiClient, putRequest: PutDataRequest): Observable<DataApi.DataItemResult> =
        DataApi.putDataItem(googleApiClient, putRequest).toObservable()

    /**
     * Adds a [DataItem] to the Android Wear network. The updated item is synchronized across all devices.
     */
    fun putItem(googleApiClient: GoogleApiClient, putRequest: PutDataMapRequest): Observable<DataApi.DataItemResult> =
        DataApi.putDataItem(googleApiClient, putRequest.asPutDataRequest()).toObservable()

    /**w
     * Retrieves a single DataItem from the Android Wear network. A fully qualified URI must be
     * specified. The URI's host must be the ID of the node that created the item.
     */
    fun getItem(googleApiClient: GoogleApiClient, uri: Uri): Observable<DataApi.DataItemResult> =
        DataApi.getDataItem(googleApiClient, uri).toObservable()

    /**
     *
     * Retrieves all data items matching the provided URI, from the Android Wear network.
     *
     * The URI must contain a path. If uri is fully specified, at most one data item will be
     * returned. If uri contains no host, multiple data items may be returned, since different
     * nodes may create data items with the same path. See DataApi for details of the URI format.
     *
     * Callers must call release() on the returned buffer when finished processing results.
     */
    fun getItems(googleApiClient: GoogleApiClient, uri: Uri): Observable<DataItemBuffer> =
        DataApi.getDataItems(googleApiClient, uri).toObservable()

    /**
     * Removes all specified data items from the Android Wear network.
     *
     * If uri is fully specified, this method will delete at most one data item. If uri contains no
     * host, multiple data items may be deleted, since different nodes may create data items with
     * the same path. See DataApi for details of the URI format.
     *
     */
    fun deleteItems(googleApiClient: GoogleApiClient, uri: Uri): Observable<DataApi.DeleteDataItemsResult> =
        DataApi.deleteDataItems(googleApiClient, uri).toObservable()

  }

}

/**
 * Adds a [DataItem] to the Android Wear network. The updated item is synchronized across all devices.
 */
fun PutDataRequest.putItem(googleApiClient: GoogleApiClient) = DataItemObservable.putItem(googleApiClient, this);

/**
 * Adds a [DataItem] to the Android Wear network. The updated item is synchronized across all devices.
 */
fun PutDataMapRequest.putItem(googleApiClient: GoogleApiClient) = DataItemObservable.putItem(googleApiClient, this);