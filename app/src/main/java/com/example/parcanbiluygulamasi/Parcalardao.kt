package com.example.parcanbiluygulamasi

class Parcalardao {

    fun rasgele5ParcaGetir(vt:VeritabaniYardimcisi) : ArrayList<Parcalar> {
        val parcalarListe = ArrayList<Parcalar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM parcalar ORDER BY RANDOM() LIMIT 5",null)

        while(c.moveToNext()){
            val parca = Parcalar(c.getInt(c.getColumnIndex("parca_id"))
                ,c.getString(c.getColumnIndex("parca_ad"))
                ,c.getString(c.getColumnIndex("parca_resim")))
            parcalarListe.add(parca)
        }

        return parcalarListe
    }

    fun rasgele3YanlisSecenekGetir(vt:VeritabaniYardimcisi,parca_id:Int) : ArrayList<Parcalar> {
        val parcalarListe = ArrayList<Parcalar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM parcalar WHERE parca_id != $parca_id ORDER BY RANDOM() LIMIT 3",null)

        while(c.moveToNext()){
            val parca = Parcalar(c.getInt(c.getColumnIndex("parca_id"))
                ,c.getString(c.getColumnIndex("parca_ad"))
                ,c.getString(c.getColumnIndex("parca_resim")))
            parcalarListe.add(parca)
        }

        return parcalarListe
    }
}