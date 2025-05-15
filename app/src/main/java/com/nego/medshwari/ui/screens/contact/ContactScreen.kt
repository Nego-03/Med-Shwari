package com.nego.medshwari.ui.screens.contact

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nego.medshwari.ui.theme.newBlue
import com.nego.medshwari.ui.theme.newLiBlue
import com.nego.medshwari.ui.theme.newWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController){

    Column(modifier = Modifier.fillMaxSize()) {

        val mContext = LocalContext.current


        //TopAppBar
        TopAppBar(
            title = { Text(text = "Contact") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = newLiBlue,
                titleContentColor = newWhite,
                navigationIconContentColor = newWhite,
                actionIconContentColor = newWhite
            ),
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "")
                }

                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                }
            }
        )
        //End

        Spacer(modifier = Modifier.height(20.dp))

        //MPESA
        Button(
            onClick = {
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            },
            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "MPESA")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Call
        Button(
            onClick = {

                val callIntent= Intent(Intent.ACTION_DIAL)
                callIntent.data="tel:0793555442".toUri()
                mContext.startActivity(callIntent)

            },
            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "Call")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //SMS
        Button(
            onClick = {

                val smsIntent=Intent(Intent.ACTION_SENDTO)
                smsIntent.data="smsto:0793555442".toUri()
                smsIntent.putExtra("sms_body","Hello Abed,how was your day?")
                mContext.startActivity(smsIntent)


            },
            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "SMS")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Share
        Button(
            onClick = {

                val shareIntent=Intent(Intent.ACTION_SEND)
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
                mContext.startActivity(Intent.createChooser(shareIntent, "Share"))

            },
            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "Share")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Camera
        Button(
            onClick = {

                val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
                    mContext.startActivity(cameraIntent)
                }else{
                    println("Camera app is not available")
                }


            },
            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "Camera")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //MPESA
        Button(
            onClick = {

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("abedmumo03@gmail.com"))
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
                mContext.startActivity(shareIntent)
            },

            colors = ButtonDefaults.buttonColors(newBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "Email")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun IntentScreenPreview(){

    ContactScreen(rememberNavController())
}
