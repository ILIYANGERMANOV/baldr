package ui.content

import core.LiveData
import logic.restClient
import core.Route
import rest.RestClient

class ContentLogic(
    private val restClient: RestClient = restClient()
) {
    var name = LiveData("")
    var tagline = LiveData("")
    var price = LiveData(0.0)
    var description = LiveData("")

    fun start(route: Route.Content) {

    }

    fun submit() {

    }
}