package Raças

class Humano (
    IncrementoForca: Int = 1,
    IncrementoDestreza: Int = 1,
    IncrementoConstituicao: Int = 1,
    IncrementoInteligencia: Int = 1,
    IncrementoSabedoria: Int = 1,
    IncrementoCarisma: Int = 1,
    RacaNome: String = "Humano"
) : Raca(
    IncrementoForca,
    IncrementoDestreza,
    IncrementoConstituicao,
    IncrementoInteligencia,
    IncrementoSabedoria,
    IncrementoCarisma,
    RacaNome
) {
    }
