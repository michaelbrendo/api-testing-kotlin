# Makefile

ALLURE_VERSION=2.34.1
ALLURE_DIR=/opt/allure
ALLURE_BIN=/usr/bin/allure
ALLURE_TGZ=/tmp/allure.tgz
ALLURE_HISTORY_DIR=allure-history
ALLURE_RESULTS_DIR=build/allure-results
ALLURE_REPORT_DIR=build/reports/allure-report/allureReport

.PHONY: test allure-report allure-serve install-allure setup

# Run all tests
test:
	@echo "🧪 Running tests..."
	./gradlew clean test --info

# Full setup: install Allure, run tests, generate report and serve
setup: install-allure test allure-report allure-serve

# Generate Allure report
allure-report:
	@echo "📊 Generating Allure report..."
	# Copy history if exists
	if [ -d $(ALLURE_HISTORY_DIR) ]; then \
		mkdir -p $(ALLURE_RESULTS_DIR)/history; \
		sudo cp -r $(ALLURE_HISTORY_DIR)/* $(ALLURE_RESULTS_DIR)/history/; \
	fi
	./gradlew allureReport --clean
	# Save history for next run
	mkdir -p $(ALLURE_HISTORY_DIR)
	sudo cp -r $(ALLURE_REPORT_DIR)/history/* $(ALLURE_HISTORY_DIR)/ || true

# Serve Allure report
allure-serve:
	@echo "🚀 Serving Allure report..."
	@which allure > /dev/null || (echo "❌ Allure CLI not found, please install it first"; exit 1)
	allure serve build/allure-results

# Install/Update Allure
install-allure:
	@INSTALLED_VERSION=$$(allure --version 2>/dev/null || echo "none"); \
	if [ "$$INSTALLED_VERSION" = "none" ]; then \
		echo "⬇️ Installing Allure $(ALLURE_VERSION)..."; \
	elif [ "$$INSTALLED_VERSION" != "$(ALLURE_VERSION)" ]; then \
		echo "🔄 Updating Allure from $$INSTALLED_VERSION to $(ALLURE_VERSION)..."; \
	else \
		echo "✅ Allure CLI already installed (version $$INSTALLED_VERSION)"; exit 0; \
	fi; \
	wget -q https://github.com/allure-framework/allure2/releases/download/$(ALLURE_VERSION)/allure-$(ALLURE_VERSION).tgz -O $(ALLURE_TGZ) && \
	sudo tar -zxf $(ALLURE_TGZ) -C /opt && \
	sudo rm -rf $(ALLURE_DIR) && \
	sudo mv /opt/allure-$(ALLURE_VERSION) $(ALLURE_DIR) && \
	sudo ln -sf $(ALLURE_DIR)/bin/allure $(ALLURE_BIN) && \
	rm -f $(ALLURE_TGZ)