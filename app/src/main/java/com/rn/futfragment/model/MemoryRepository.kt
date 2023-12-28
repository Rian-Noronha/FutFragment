package com.rn.futfragment.model

object MemoryRepository : PlayerRepository{

    private var nextId = 1L;
    private val playersList = mutableListOf<Player>()

    init {
        save(Player(0, "Vitor Roque", 4, 2, 9, "Brazilian", "Barcelona", "Centroavante", 78F, 1.72F, 3F))
        save(Player(0, "Emma Johnson", 7, 3, 12, "English", "Manchester United", "Midfielder", 85.5F, 1.68F, 2F))
        save(Player(0, "Juan Perez", 2, 5, 11, "Spanish", "Real Madrid", "Defender", 79.2F, 1.80F, 0.5F))
        save(Player(0, "Mia Wang", 5, 8, 10, "Chinese", "Beijing Guoan", "Forward", 88.0F, 1.75F, 3F))
        save(Player(0, "Alex Oliveira", 3, 1, 14, "Portuguese", "FC Porto", "Goalkeeper", 82.7F, 1.90F, 1.5F))
        save(Player(0, "Sophie Smith", 6, 4, 8, "American", "LA Galaxy", "Winger", 87.3F, 1.70F, 1.4F))
        save(Player(0, "Mateo Rodriguez", 1, 7, 13, "Argentine", "Boca Juniors", "Midfielder", 83.8F, 1.76F, 3.8F))
        save(Player(0, "Liam Taylor", 4, 3, 12, "Canadian", "Toronto FC", "Defender", 81.0F, 1.78F, 4.3F))
        save(Player(0, "Liam Ryan", 40, 33, 15, "Canadian", "Trost FC", "Defender", 81.0F, 1.78F, 5.0F))
    }

    override fun save(player: Player) {
        if(player.id == 0L){
            player.id = nextId++
            playersList.add(player)
        }else{
            val index = playersList.indexOfFirst { it.id == player.id }
            if(index > -1){
                playersList[index] = player
            }else{
                playersList.add(player)
            }
        }
    }

    override fun remove(vararg players: Player) {
        playersList.removeAll(players)
    }

    override fun playerById(id: Long, callback: (Player?) -> Unit) {
       callback(playersList.find{it.id == id})
    }

    override fun search(term: String, callback: (List<Player>) -> Unit) {
        callback(
            if(term.isEmpty()) playersList
            else playersList.filter{
                it.name.uppercase().contains(term.uppercase())
            }
        )
    }


}