#Makefile

ALLURE_VERSION=2.34.1
ALLURE_DIR=/opt/allure

#Run all test
test:
	@echo "Running tests..."
	./gradlew clean test --info

allure-report:
	@echo "Generating Allure report..."
	./gradlew allureReport

allure-serve:
	@echo "Serving Allure report..."
	@which allure > /dev/null || (echo "Allure CLI not found, please install it first"; exit 1)
	allure serve build/allure-results

install-allure:
	@INSTALLED_VERSION=$$(allure --version 2>/dev/null || echo "none"); \
	if [ "$$INSTALLED_VERSION" = "none" ]; then \
		echo "Allure not found. Installing version $(ALLURE_VERSION)..."; \
		wget https://github.com/allure-framework/allure2/releases/download/$(ALLURE_VERSION)/allure-$(ALLURE_VERSION).tgz -O /tmp/allure.tgz && \
		sudo tar -zxvf /tmp/allure.tgz -C /opt && \
		sudo rm -rf $(ALLURE_DIR) && \
		sudo mv /opt/allure-$(ALLURE_VERSION) $(ALLURE_DIR) && \
		sudo ln -sf $(ALLURE_DIR)/bin/allure /usr/bin/allure; \
	elif [ "$$INSTALLED_VERSION" != "$(ALLURE_VERSION)" ]; then \
		echo "Different Allure version detected ($$INSTALLED_VERSION). Updating to $(ALLURE_VERSION)..."; \
		wget https://github.com/allure-framework/allure2/releases/download/$(ALLURE_VERSION)/allure-$(ALLURE_VERSION).tgz -O /tmp/allure.tgz && \
		sudo tar -zxvf /tmp/allure.tgz -C /opt && \
		sudo rm -rf $(ALLURE_DIR) && \
		sudo mv /opt/allure-$(ALLURE_VERSION) $(ALLURE_DIR) && \
		sudo ln -sf $(ALLURE_DIR)/bin/allure /usr/bin/allure; \
	else \
		echo "Allure CLI already installed and up-to-date (version $$INSTALLED_VERSION)"; \
	fi

