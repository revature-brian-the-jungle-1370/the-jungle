from behave import given,when,then
from selenium.webdriver.common.by import By

@given(u'I am on the Login Page')
def step_impl(context):
    context.driver.get("https://s3.amazonaws.com/dans-code.net/FrontEnd/loginpage/login.html")

@when(u'I click reset password button')
def click_reset_password_link(context):
    context.reset_password_link.click()
    

@when(u'I am taken to the reset password page')
def go_to_reset_password_page(context):
    

@when(u'I enter the email associated to the User')
def enter_user_email(context):
    raise NotImplementedError(u'STEP: When I enter the email associated to the User')


@when(u'I am taken to the new password page')
def go_to_enter_new_password_page(context):
    context.get("localhost:5000/user")


@when(u'I input a new password')
def enter_new_password(context):
    context.password_text_box.sendkeys("newpasscode")

@when(u'and click the reset password button')
def click_reset_button(context):
    context.create_new_password().click()


@then(u'I will login with my new password')
def login_with_new_pasword(context):
        context.username_text_box().sendkeys("email")
        context.password_text_box().sendkeys("newpasscode")
        context.login_button.click()
        assert context.driver.title == Home