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
        case(id = "helloCase", name = "Hello Case") {
            planModel(id = "planModel", name = "Plan Model") {
                planItem(id = "planItem1", definitionRef = "helloTask")
                javaServiceTask(id = "helloTask", name = "Hello Task", delegateExpression = "\${helloDelegate}")
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
