package com.example.sos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random


class TwoPlayer : AppCompatActivity() {

    var imageViewList = listOf<ImageView>()

    private lateinit var playerOneLayout: LinearLayout
    private lateinit var playerTwoLayout: LinearLayout

    private lateinit var playerOnePuanTV: TextView
    private lateinit var playerTwoPuanTV: TextView


    private lateinit var playerOneSelectS: ImageView
    private lateinit var playerOneSelectO: ImageView
    private lateinit var playerTwoSelectS: ImageView
    private lateinit var playerTwoSelectO: ImageView
    private var playerTurn = 1
    private var totalSelectedBoxes = 0
    private var playerOnePuan=0
    private var playerTwoPuan=0


    private var boxPositions = intArrayOf(2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2)

    var playerOneSecilen = 1
    var playerTwoSecilen = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_two_player)

        playerOneLayout = findViewById(R.id.playerOneLayout)
        playerTwoLayout = findViewById(R.id.playerTwoLayout)

        playerOnePuanTV = findViewById(R.id.playerOnePuanTv)
        playerTwoPuanTV = findViewById(R.id.playerTwoPuanTV)

        playerOneSelectS = findViewById(R.id.playerOneSelectS)
        playerOneSelectO = findViewById(R.id.playerOneSelectO)
        playerTwoSelectS = findViewById(R.id.playerTwoSelectS)
        playerTwoSelectO = findViewById(R.id.playerTwoSelectO)

        imageViewList = listOf<ImageView>(findViewById(R.id.image0),findViewById(R.id.image1), findViewById(R.id.image2), findViewById(R.id.image3), findViewById(R.id.image4), findViewById(R.id.image5), findViewById(R.id.image6),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image9), findViewById(R.id.image10), findViewById(R.id.image11), findViewById(R.id.image12), findViewById(R.id.image13), findViewById(R.id.image14), findViewById(R.id.image15),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image18), findViewById(R.id.image19), findViewById(R.id.image20), findViewById(R.id.image21), findViewById(R.id.image22), findViewById(R.id.image23), findViewById(R.id.image24),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image27), findViewById(R.id.image28), findViewById(R.id.image29), findViewById(R.id.image30), findViewById(R.id.image31), findViewById(R.id.image32), findViewById(R.id.image33),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image36), findViewById(R.id.image37), findViewById(R.id.image38), findViewById(R.id.image39), findViewById(R.id.image40), findViewById(R.id.image41), findViewById(R.id.image42),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image45), findViewById(R.id.image46), findViewById(R.id.image47), findViewById(R.id.image48), findViewById(R.id.image49), findViewById(R.id.image50), findViewById(R.id.image51),playerOneSelectS,playerOneSelectS
            , findViewById(R.id.image54), findViewById(R.id.image55), findViewById(R.id.image56), findViewById(R.id.image57), findViewById(R.id.image58), findViewById(R.id.image59), findViewById(R.id.image60),playerOneSelectS,playerOneSelectS
        )

        playerOneSelectO.setOnClickListener {
            playerOneSecilen=0
            playerOneSelectO.setBackgroundResource(R.drawable.round_back_dark_blue_stroke) // seçileni gösteriyor
            playerOneSelectS.setBackgroundResource(R.drawable.trasnparent)
        }
        playerOneSelectS.setOnClickListener {
            playerOneSecilen=1
            playerOneSelectS.setBackgroundResource(R.drawable.round_back_dark_blue_stroke)
            playerOneSelectO.setBackgroundResource(R.drawable.trasnparent)
        }
        playerTwoSelectO.setOnClickListener {
            playerTwoSecilen=0
            playerTwoSelectO.setBackgroundResource(R.drawable.round_back_dark_blue_stroke)
            playerTwoSelectS.setBackgroundResource(R.drawable.trasnparent)
        }
        playerTwoSelectS.setOnClickListener {
            playerTwoSecilen=1
            playerTwoSelectS.setBackgroundResource(R.drawable.round_back_dark_blue_stroke)
            playerTwoSelectO.setBackgroundResource(R.drawable.trasnparent)
        }


        val numbers = mutableSetOf<Int>()

        while (numbers.size < 9) {
            val randomNumber = Random.nextInt(0, 63)
            numbers.add(randomNumber)
        }

        val randomNumbersList = numbers.toList()
        for (number in randomNumbersList) {
            imageViewList[number].setImageResource(R.drawable.cross)
        }


    }


    fun hamleYap(view: View?){

        val imageView = view as ImageView
        var box = imageView.getTag().toString().toInt()
        println(box)
        var secilen=0

        if (playerTurn==1){
            secilen=playerOneSecilen
        }else{
            secilen=playerTwoSecilen
        }

        if(boxPositions[box] == 2) {
            if (secilen == 0) {

                boxPositions[box] = 0
                println(boxPositions[box])
                imageView.setImageResource(R.drawable.circle)
                totalSelectedBoxes++
                puanKontrol(box)

            } else if (secilen == 1) {

                boxPositions[box] = 1
                println(boxPositions[box])
                imageView.setImageResource(R.drawable.cross)
                totalSelectedBoxes++
                puanKontrol(box)

            } else {
                println("Seçilemez")
            }


        }

    }

    fun puanKontrol(box:Int){

        var puanEklendiMi=false

        if(boxPositions[box] == 0){

            if (box -1 >= 0 && box +1 <=60) {
                if(boxPositions[box -1] == 1 && boxPositions[box + 1] == 1){
                    // SOS yan yana olmuştur +1 puan
                    imageViewList[box-1].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+1].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if ((box -9) >= 0 && (box +9) <=60){
                if(boxPositions[box -9] == 1 && boxPositions[box + 9] == 1){
                    // SOS üstü ve altı olmuştur +1 puan
                    imageViewList[box-9].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+9].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -10 >= 0 && box +10 <=60){
                if(boxPositions[box -10] == 1 && boxPositions[box + 10] == 1){
                    // SOS sol çaprazdan sağ çapraz olmuştur +1 puan
                    imageViewList[box-10].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+10].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -8 >= 0 && box +8 <=60){
                if(boxPositions[box -8] == 1 && boxPositions[box + 8] == 1){
                    // SOS sağ çapraz sol çapraz olmuştur +1 puan
                    imageViewList[box-8].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+8].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }

        }
        if(boxPositions[box] == 1){ // yani S ise

            if (box +1 <= 60 && box +2 <= 60){
                if(boxPositions[box +1] == 0 && boxPositions[box + 2] == 1){
                    // SOS sağındaki O onun sağındaki de S ise olmuştur +1 puan
                    imageViewList[box+1].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+2].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -1 >= 0 && box -2 >= 0){
                if(boxPositions[box -1] == 0 && boxPositions[box -2] == 1){
                    // SOS solundaki 0 onunda solundaki O ise olmuştur +1 puan
                    imageViewList[box-1].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box-2].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box +9 <= 60 && box +18 <=60){
                if(boxPositions[box +9] == 0 && boxPositions[box + 18] == 1){
                    // SOS aşağıdaki O onun aşağıdaki de S ise olmuştur +1 puan
                    imageViewList[box+9].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+18].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -9 >= 0 && box -18 >= 0){
                if(boxPositions[box -9] == 0 && boxPositions[box -18] == 1){
                    // SOS yukardaki O onun yukardaki de S ise olmuştur +1 puan
                    imageViewList[box-9].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box-18].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box +10 <= 60 && box +20 <=60){
                if(boxPositions[box +10] == 0 && boxPositions[box +20 ] == 1){
                    // SOS soldan aşağı çaprazdan 0 onunda soldan aşağısı çaprazdan S ise olmuştur +1 puan
                    imageViewList[box+10].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+20].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -10 >= 0 && box -20 >= 0){
                if(boxPositions[box -10] == 0 && boxPositions[box -20] == 1){
                    // SOS soldan yukarı çaprazdan 0 onunda soldan yukarı çaprazdan S ise olmuştur +1 puan
                    imageViewList[box-10].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box-20].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box +8 <= 60 && box +16 <=60){
                if(boxPositions[box +8] == 0 && boxPositions[box +16 ] == 1){
                    // SOS sağdan aşağı çaprazdan 0 onunda sağdan aşağısı çaprazdan S ise olmuştur +1 puan
                    imageViewList[box+8].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box+16].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }
            if (box -8 >= 0 && box -16 >= 0){
                if(boxPositions[box -8] == 0 && boxPositions[box -16] == 1){
                    // SOS sağdan yukarı çaprazdan 0 onunda sağdan yukarı çaprazdan S ise olmuştur +1 puan
                    imageViewList[box-8].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box].setBackgroundResource(R.drawable.round_back_blue_100)
                    imageViewList[box-16].setBackgroundResource(R.drawable.round_back_blue_100)
                    puanEkle()
                    puanEklendiMi=true
                }
            }

        }



        if (puanEklendiMi==false){
            if (playerTurn==1){
                changePlayerTurn(2)
            }
            else if (playerTurn==2){
                changePlayerTurn(1)
            }
        }

        if (totalSelectedBoxes==49){
            println("Oyun Bitti")
        }


    }

    private fun changePlayerTurn(currentPlayerTurn : Int){

        playerTurn = currentPlayerTurn

        if (playerTurn == 1){

            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue_stroke)
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue_20)
        }
        else{

            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue_stroke)
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue_20)

        }
    }

    fun restartMatch(){

        boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        playerTurn = 1

        totalSelectedBoxes = 1

        /*image1.setImageResource(R.drawable.trasnparent)
        image2.setImageResource(R.drawable.trasnparent)
        image3.setImageResource(R.drawable.trasnparent)
        image4.setImageResource(R.drawable.trasnparent)
        image5.setImageResource(R.drawable.trasnparent)
        image6.setImageResource(R.drawable.trasnparent)
        image7.setImageResource(R.drawable.trasnparent)
        image8.setImageResource(R.drawable.trasnparent)
        image9.setImageResource(R.drawable.trasnparent)*/

    }

    fun puanEkle(){

        if (playerTurn==1){
            playerOnePuan++
            playerOnePuanTV.text=playerOnePuan.toString()
        }
        else if (playerTurn==2){
            playerTwoPuan++
            playerTwoPuanTV.text=playerTwoPuan.toString()
        }



    }





}