terraform {
  cloud {
    organization = "CN-Rail"
 
    workspaces {
      tags = ["ws:scio-oms-app_service"]      
    }
  }
}