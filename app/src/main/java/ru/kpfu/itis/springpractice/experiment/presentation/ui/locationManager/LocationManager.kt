package ru.kpfu.itis.springpractice.experiment.presentation.ui.locationManager;

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationManager(
    val activity: Activity
) {
    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)

    suspend fun getLocation(): Location? = suspendCoroutine { cont ->
        // todo: move asking the permissions into the fragment
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        println("LOCATION MANAGER TEST TAG - perceived location Lat: ${it.latitude}, Lng: ${it.longitude}")
                        cont.resume(Location(
                            longitude = location.longitude,
                            latitude = location.latitude)
                        )
                    }

                }
                .addOnFailureListener { e ->
                    cont.resume(null)
                }
        } else {
            // todo: move asking the permissions to the fragment part
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
            cont.resume(null)
        }
    }
}
