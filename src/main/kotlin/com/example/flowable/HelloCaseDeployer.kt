package com.example.flowable

import org.flowable.cmmn.api.CmmnRepositoryService
import org.flowable.cmmn.converter.CmmnXmlConverter
import org.flowable.cmmn.model.CmmnModel
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class HelloCaseDeployer(private val cmmnRepositoryService: CmmnRepositoryService) : ApplicationRunner {

    private fun buildModel(): CmmnModel = cmmnModel {
        case(name = "Hello Case") {
            planModel(name = "Plan Model") {
                javaServiceTask(name = "Hello Task", delegateExpression = "\${helloDelegate}")
            }
        }
    }

    override fun run(args: ApplicationArguments) {
        val xml = CmmnXmlConverter().convertToXML(buildModel())
        cmmnRepositoryService.createDeployment()
            .addBytes("hello-case.cmmn", xml)
            .deploy()
    }
}
