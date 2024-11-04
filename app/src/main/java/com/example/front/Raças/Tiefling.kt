package Raças

class Tiefling (
    IncrementoForca: Int = 0,
    IncrementoDestreza: Int = 0,
    IncrementoConstituicao: Int = 0,
    IncrementoInteligencia: Int = 1,
    IncrementoSabedoria: Int = 0,
    IncrementoCarisma: Int = 2,
    RacaNome: String = "Tiefling"
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