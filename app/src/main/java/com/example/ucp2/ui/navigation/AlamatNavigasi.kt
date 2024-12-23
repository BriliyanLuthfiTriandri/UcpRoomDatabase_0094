package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiAwal : AlamatNavigasi{
    override val route = "homeAwal"
}

object DestinasiHomeBrg : AlamatNavigasi{
    override val route:String = "homeBarang"
}


object DestinasiHomeSplr : AlamatNavigasi{
    override val route:String = "homeSuplier"
}

object DestinasiInsertBrg : AlamatNavigasi{
    override val route:String = "insertBarang"
}


object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detailBarang"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "updateBarang"
    const val ID = "id"
    val routesWithArg = "${route}/{${ID}}"
}


object DestinasiInsertSplr : AlamatNavigasi{
    override val route:String = "insertSuplier"
}




