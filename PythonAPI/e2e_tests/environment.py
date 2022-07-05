from behave.runner import Context
from selenium import webdriver


def before_all(context: Context):
    context.driver = webdriver.Chrome("e2e_tests/chromedriver.exe")
    context.driver.implicitly_wait(10)


def after_all(context):
    context.driver.quit()
