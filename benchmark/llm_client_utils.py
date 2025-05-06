import os
from openai import AzureOpenAI
from azure.identity import DefaultAzureCredential
from dotenv import load_dotenv

def initialize_llm_client(model=None, endpoint=None):
    """Initialize and return the Azure OpenAI client."""
    load_dotenv()

    # Set environment variables
    os.environ["OPENAI_API_VERSION"] = "2025-01-01-preview"
    os.environ["AZURE_OPENAI_ENDPOINT"] = "https://haozh-m78pt7gv-swedencentral.openai.azure.com"
    os.environ["AZURE_OPENAI_DEPLOYMENT"] = "gpt-4o"
    os.environ["AZURE_OPENAI_NONFUNCTIONAL_CHECK_DEPLOYMENT"] = "gpt-4o-mini"
    os.environ["AZURE_OPENAI_ENDPOINT_REVIEW"] = "https://kaiqi-m9ghu5ar-northcentralus.cognitiveservices.azure.com/"
    os.environ["AZURE_OPENAI_FORMULA_CHECK_DEPLOYMENT"] = "gpt-4o"
    os.environ["BACKUP_ENDPOINT"] = "https://ruoyu-ai.openai.azure.com"
    
    # Get model from parameter or environment variable
    model = model or os.environ.get("AZURE_OPENAI_DEPLOYMENT")

    endpoint = endpoint or os.environ.get("AZURE_OPENAI_ENDPOINT")
    
    # Use key-based authentication if api_key is set, otherwise use Azure AD token
    api_key = os.environ.get("AZURE_OPENAI_API_KEY")
    if api_key:
        return AzureOpenAI(
            azure_deployment=model,
            azure_endpoint=endpoint,
            timeout=360,
        )
    else:
        credential = DefaultAzureCredential()
        token = credential.get_token("https://cognitiveservices.azure.com/.default")
        return AzureOpenAI(
            azure_deployment=model,
            azure_endpoint=endpoint,
            azure_ad_token_provider=lambda: token.token,
            timeout=360,
        )