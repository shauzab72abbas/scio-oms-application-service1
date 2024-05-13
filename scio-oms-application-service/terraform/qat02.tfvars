project_id="cnr-sciomicrosvc-qat02-1b0w"
location="northamerica-northeast1"
service_account_email="cnr-scio-app-service-run@cnr-sciomicrosvc-qat02-1b0w.iam.gserviceaccount.com"
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
          "name" = "breURL"
          "value" = "#{breURL}#"
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
          "name" = "breILBUrl"
          "value" = "#{breILBUrl}#"
    }
]
template_annotations = {
    "run.googleapis.com/client-name"   = "terraform"
    "generated-by"                     = "terraform"
    "autoscaling.knative.dev/maxScale" = #{maxInstances}#
    "autoscaling.knative.dev/minScale" = #{minInstances}#
    "run.googleapis.com/vpc-access-connector" = "projects/cnr-sciotelecom-npd-5k9i/locations/northamerica-northeast1/connectors/gne1-serverlessvpc-con01q"
    "run.googleapis.com/vpc-access-egress" = "#{egressValue}#"
    "run.googleapis.com/cpu-throttling" = "#{cpuThrottling}#"
    "run.googleapis.com/startup-cpu-boost" = "true"
  }
service_annotations = {
    "run.googleapis.com/ingress" = "#{ingressValue}#"
    // "run.googleapis.com/binary-authorization" = "default"
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