from behave import given,when,then
from selenium.webdriver.common.by import By

@given(u'I am on the Login Page')
def step_impl(context):
    context.driver.get("file:///C:/Users/tyler/Repositories/220516-Python-with-Automation/Project%203/the-jungle/FrontEnd/loginpage/login.html")

@when(u'I click reset password button')
def click_reset_password_link(context):
    context.reset_password_link.click()
    

@when(u'I am taken to the reset password page')
def go_to_reset_password_page(context):
    assert context.driver.title == "Reset Password"

@when(u'I enter the email associated to the User')
def enter_user_email(context):
    context.driver.email_text_box().send_keys("email")
    context.driver.continue_to_reset_password().click()


@when(u'I am taken to the new password page')
def go_to_enter_new_password_page(context):
    assert context.driver.title == "Enter New Password"


@when(u'I input a new password')
def enter_new_password(context):
    context.driver.password_text_box.send_keys("newpasscode")

@when(u'click the reset password button')
def click_reset_button(context):
    context.driver.create_new_password().click()


@then(u'I will login with my new password')
def login_with_new_pasword(context):
        context.username_text_box().send_keys("email")
        context.password_text_box().send_keys("newpasscode")
        context.login_button.click()
        assert context.driver.title == "Home"