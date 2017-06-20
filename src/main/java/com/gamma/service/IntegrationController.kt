package com.gamma.service

import com.gamma.configuration.IntegrationConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by guushamm on 20-6-17.
 */
@RestController
class IntegrationController{
    @Autowired
    lateinit var integrationConfiguration: IntegrationConfiguration

    @RequestMapping(value = "/reportstolencar")
    fun reportCarStolen(@RequestParam("licensePlate") licensePlate: String){
        integrationConfiguration.reportCarStolen(licensePlate = licensePlate)
    }
}
