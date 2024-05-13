project_id = "#{project_id}#"
location = "#{location}#"
service_account_email = "#{service_account_email}#"
resource-count = #{resource-count}#
env_vars = [
    {
        "name" = "REFERENCEDOMAINURL"
        "value" = "#{referenceDomainUrl}#"
    },
    {
        "name" = "CUSTOMERDOMAINURL"
        "value" = "#{customerDomainUrl}#"
    },
    {
        "name" = "ORDERDOMAINURL"
        "value" = "#{orderDomainUrl}#"
    },
    {
        "name" = "REFERENCEDOMAINILBURL"
        "value" = "#{referenceDomainILBUrl}#"
    },
    {
        "name" = "CUSTOMERDOMAINILBURL"
        "value" = "#{customerDomainILBUrl}#"
    },
    {
        "name" = "ORDERDOMAINILBURL"
        "value" = "#{orderDomainILBUrl}#"
    },
    {
        "name" = "breURL"
        "value" = "#{breURL}#"
    },
    {
        "name" = "breILBUrl"
        "value" = "#{breILBUrl}#"
    }
]
template_annotations = {
    "run.googleapis.com/client-name"   = "terraform"
    "generated-by"                     = "terraform"
    "autoscaling.knative.dev/maxScale" = #{maxInstances}#
    "autoscaling.knative.dev/minScale" = #{minInstances}#
    "run.googleapis.com/vpc-access-connector" = "#{accessConnector}#"
    "run.googleapis.com/vpc-access-egress" = "#{egressValue}#"
    "run.googleapis.com/cpu-throttling" = "#{cpuThrottling}#"
    "run.googleapis.com/startup-cpu-boost" = "#{cpuBoost}#"
  }
service_annotations = {
    "run.googleapis.com/ingress" = "#{ingressValue}#"
    "run.googleapis.com/binary-authorization" = "default"
  }
ports = {
    "name" = "#{portName}#"
    "port" = #{port}#
}

limits = {
    "memory" = "#{memoryLimit}#"
    "cpu" = "#{cpuLimit}#"
}
requests = {
    "memory" = "#{memoryRequest}#"
    "cpu" = "#{cpuRequest}#"
}
container_concurrency = #{containerConcurrency}#
timeout_seconds= #{timeoutSeconds}#
service_labels = {
    "cm_application_service_num" = "#{cm_application_service_num}#"
    "cm_service_offering_num"    = "#{cm_service_offering_num}#"
    "cm_used_for"                = "#{cm_used_for}#"
    "cm_environment"             = "#{cm_environment}#"
    "app_name"                   = "#{app_name}#"
}

####################### DR ##################################
dr-location = "#{dr-location}#"
dr-resource = #{dr-resource}#
dr-env_vars = [
    {
        "name" = "REFERENCEDOMAINURL"
        "value" = "#{drReferenceDomainUrl}#"
    },
    {
        "name" = "CUSTOMERDOMAINURL"
        "value" = "#{drCustomerDomainUrl}#"
    },
    {
        "name" = "ORDERDOMAINURL"
        "value" = "#{drOrderDomainUrl}#"
    },
    {
        "name" = "REFERENCEDOMAINILBURL"
        "value" = "#{drReferenceDomainILBUrl}#"
    },
    {
        "name" = "CUSTOMERDOMAINILBURL"
        "value" = "#{drCustomerDomainILBUrl}#"
    },
    {
        "name" = "ORDERDOMAINILBURL"
        "value" = "#{drOrderDomainILBUrl}#"
    },
    {
        "name" = "breURL"
        "value" = "#{drBreURL}#"
    },
    {
        "name" = "breILBUrl"
        "value" = "#{drBreILBUrl}#"
    }
]
dr-template_annotations = {
    "run.googleapis.com/client-name"   = "terraform"
    "generated-by"                     = "terraform"
    "autoscaling.knative.dev/maxScale" = #{maxInstances}#
    "autoscaling.knative.dev/minScale" = #{minInstances}#
    "run.googleapis.com/vpc-access-connector" = "#{drAccessConnector}#"
    "run.googleapis.com/vpc-access-egress" = "#{egressValue}#"
    "run.googleapis.com/cpu-throttling" = "#{cpuThrottling}#"
    "run.googleapis.com/startup-cpu-boost" = "#{cpuBoost}#"
  }