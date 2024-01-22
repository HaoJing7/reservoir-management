<template>
  <div class="login-container">
    <div class="logo" />
    <div class="form">
      <h1>管 理 员 登 录</h1>
      <el-card shadow="never" class="login-card">
        <!--登录表单-->
        <!-- el-form > el-form-item > el-input -->
        <el-form ref="form" :model="loginForm" :rules="loginRules">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" show-password placeholder="请输入密码" />
          </el-form-item>
          <el-form-item>
            <div style="display: flex">
              <el-input v-model="loginForm.validCode" style="width: 60%" placeholder="请输入验证码" size="medium"></el-input>
              <ValidCode @input="createValidCode" />
            </div>
          </el-form-item>
          <el-form-item prop="isAgree">
            <el-checkbox v-model="loginForm.isAgree">
              用户平台使用协议
            </el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-button style="width:350px" type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>
<script>
import ValidCode from "@/components/ValidCode.vue";
export default {
  name: 'Login',
  components: {
    ValidCode,
  },
  data() {
    return {
      loginForm: {
        username: process.env.NODE_ENV === 'development' ? 'admin' : '',
        password: process.env.NODE_ENV === 'development' ? '123456' : '',
        isAgree: process.env.NODE_ENV === 'development'
      },
      loginRules: {
        username: [{
          required: true,
          message: '请输入用户名',
          trigger: 'blur'
        }, {
          min: 3,
          max: 16,
          message: '用户名长度应该为3-16位之间',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }, {
          min: 3,
          max: 16,
          message: '密码长度应该为3-16位之间',
          trigger: 'blur'
        }],
        // required只能检测 null undefined ""
        isAgree: [{
          validator: (rule, value, callback) => {
            // rule校验规则
            // value 校验的值
            // callback 函数 - promise resolve reject
            // callback() callback(new Error(错误信息))
            value ? callback() : callback(new Error('您必须勾选用户的使用协议'))
          }
        }]
      },
      validCode: '',
    }
  },
  methods: {
    //接收验证码组件提交的4位验证码
    createValidCode(data) {
      this.validCode = data
    },
    login() {
      this.$refs.form.validate(async(isOK) => {
        if (isOK) {
          if (!this.loginForm.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if (this.loginForm.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          await this.$store.dispatch('user/login', this.loginForm)
          // Vuex 中的action 返回的promise
          // 跳转主页
          this.$router.push('/')
        }
      })
    },
  }
}
</script>
<style lang="scss">
.login-container {
  display: flex;
  align-items: stretch;
  height: 100vh;
  .logo {
    flex: 3;
    background: rgba(38, 72, 176) url(../../assets/common/login_back.jpg)
      no-repeat center / cover;
    border-top-right-radius: 60px;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    justify-content: center;
    padding: 0 100px;
    .icon {
      background: url(../../assets/common/logo.png) no-repeat 70px center /
        contain;
      width: 300px;
      height: 50px;
      margin-bottom: 50px;
    }
    p {
      color: #fff;
      font-size: 18px;
      margin-top: 20px;
      width: 300px;
      text-align: center;
    }
  }
  .form {
    flex: 2;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 176px;
    .el-card {
      border: none;
      padding: 0;
    }
    h1 {
      padding-left: 20px;
      font-size: 24px;
    }
    .el-input {
      width: 350px;
      height: 44px;
      .el-input__inner {
        background: #f4f5fb;
      }
    }
    .el-checkbox {
      color:#606266;
    }
  }
}
</style>
