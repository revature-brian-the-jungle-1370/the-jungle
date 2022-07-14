from behave import given,when,then
from selenium.webdriver.common.by import By

@given(u'I am on the Login Page')
def step_impl(context):
    pass


@when(u'I click reset password button')
def step_impl(context):
    context.reset_password_link.click()
    explicit


@when(u'I am taken to the reset password page')
def step_impl(context):
    
    raise NotImplementedError(u'STEP: When I am taken to the reset password page')


@when(u'I enter the email associated to the User')
def step_impl(context):
    raise NotImplementedError(u'STEP: When I enter the email associated to the User')


@when(u'I am taken to the new password page')
def step_impl(context):
    raise NotImplementedError(u'STEP: When I am taken to the new password page')


@when(u'I input a new password')
def step_impl(context):
    context.password_text_box.sendkeys("newpasscode")

@when(u'and click the reset password button')
def click_reset_button(context):
    context.create_new_password().click()


@then(u'I will login with my new password')
def login_with_new_pasword(context):
        context.username_text_box().sendkeys("email")
        context.password_text_box().sendkeys("newpasscode")
        context.login_button.click()