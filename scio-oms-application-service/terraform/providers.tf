##############################################################
# Provider configuration for SCIO GCP
##############################################################

terraform {
  required_providers {
	google-beta = {
      source = "hashicorp/google-beta"
      version = "4.48.0"
    }
  }
}
