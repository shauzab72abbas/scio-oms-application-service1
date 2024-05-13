################# DR ####################
module "scio-oms-application-dr"{
  count = (var.env == "uat" || var.env == "uat02" || var.env == "prd") && var.dr-resource == 1 ? 1 : 0
  source = "git::https://gitprd.cn.ca/scm/gcpsciot/cloud-run-module//cloud-run?ref=feature/probes"
  project_id = var.project_id
  service_name = var.SERVICE_NAME
  location = var.dr-location
  image = var.IMAGE
  service_account_email = var.service_account_email
  env_vars   = var.dr-env_vars
  limits = var.limits
  requests = var.requests
  template_annotations = var.dr-template_annotations
  service_annotations = var.service_annotations
  container_concurrency = var.container_concurrency
  ports = var.ports
  service_labels = var.service_labels
}

################# Main ####################
module "scio-oms-application"{
  source = "git::https://gitprd.cn.ca/scm/gcpsciot/cloud-run-module//cloud-run?ref=feature/probes"
  count = var.resource-count
  project_id = var.project_id
  service_name = var.SERVICE_NAME
  location = var.location
  image = var.IMAGE
  service_account_email = var.service_account_email  
  env_vars   = var.env_vars
  limits = var.limits
  requests = var.requests
  template_annotations = var.template_annotations
  service_annotations = var.service_annotations
  container_concurrency = var.container_concurrency
  ports = var.ports
  service_labels = var.service_labels
}

