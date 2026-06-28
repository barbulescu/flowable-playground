package com.example.flowable

import org.flowable.cmmn.api.CmmnRepositoryService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class HelloCaseDeployer(private val cmmnRepositoryService: CmmnRepositoryService) : ApplicationRunner {

    private val cmmnXml = """<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/CMMN/20151109/MODEL"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:flowable="http://flowable.org/cmmn"
             xmlns:cmmndi="http://www.omg.org/spec/CMMN/20151109/CMMNDI"
             xmlns:dc="http://www.omg.org/spec/CMMN/20151109/DC"
             targetNamespace="http://flowable.org/cmmn">
    <case id="helloCase" name="Hello Case">
        <casePlanModel id="planModel" name="Plan Model">
            <planItem id="planItem1" definitionRef="helloTask"/>
            <task id="helloTask" name="Hello Task"
                  flowable:type="java"
                  flowable:delegateExpression="${'$'}{helloDelegate}"/>
        </casePlanModel>
    </case>
</definitions>"""

    override fun run(args: ApplicationArguments) {
        cmmnRepositoryService.createDeployment()
            .addString("hello-case.cmmn", cmmnXml)
            .deploy()
    }
}
