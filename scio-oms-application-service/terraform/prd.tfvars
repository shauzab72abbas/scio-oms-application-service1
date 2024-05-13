project_id="cnr-sciomicroservice-prd-29ik"
location="northamerica-northeast1"
service_account_email="cnr-scio-app-service-run@cnr-sciomicroservice-prd-29ik.iam.gserviceaccount.com"
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
    "run.googleapis.com/vpc-access-connector" = "projects/cnr-sciotelecom-prd-16uu/locations/northamerica-northeast1/connectors/gne1-serverlessvpc-con01p"
    "run.googleapis.com/vpc-access-egress" = "#{egressValue}#"
    "run.googleapis.com/cpu-throttling" = "#{cpuThrottling}#"
    "run.googleapis.com/startup-cpu-boost" = "true"
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