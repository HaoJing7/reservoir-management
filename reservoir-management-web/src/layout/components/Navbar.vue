<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar"/>

    <breadcrumb class="breadcrumb-container"/>

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img src="@/assets/avatar.png" class="user-avatar">
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <!--个人信息部分自行开发-->
          <el-dropdown-item @click.native="userInfoVisible=true;">
            个人信息
          </el-dropdown-item>

          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">注销</span>
          </el-dropdown-item>

          <!-- 删除该用户功能的开发（需要先检验是否能删除） -->
          <el-dropdown-item divided @click.native="inputAnswerTableVisible=true;inputAnswer='';">
            <span style="display:block;">删除该用户</span>
          </el-dropdown-item>

        </el-dropdown-menu>
      </el-dropdown>

      <!-- 删除账号时回答密保问题 -->
      <el-dialog title="回答密保问题" :visible.sync="inputAnswerTableVisible" width="400px">
        <p>问题：{{ $store.state.user.problem }}</p>
        <p>答案：</p>
        <el-input v-model="inputAnswer" placeholder="请输入答案" clearable style="background-color: #eff5f5"></el-input>
        <el-button type="primary" @click="checkAnswer" style="margin-top: 10px">确认答案</el-button>
      </el-dialog>

      <!-- 个人信息 -->
      <el-dialog :visible.sync="userInfoVisible" width="400px">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><b style="font-size: 15px">个人信息</b></span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="inputAnswerTableVisible=true;inputAnswer2='';">修改个人信息</el-button>
          </div>
          <div>账号：{{ $store.state.user.name }}</div>
          <div>密码：{{ $store.state.user.password }}</div>
          <div>密保问题：{{ $store.state.user.problem }}</div>
          <div>答案：*********</div>
        </el-card>
      </el-dialog>
      <!-- 个人信息修改 -->
      <el-dialog title="个人信息修改" :visible.sync="userFormVisible">
        <el-form :model="userForm">
          <el-form-item label="用户名" label-width="120px">
            <el-input v-model="userForm.username" disabled autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="userForm.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密保问题" label-width="120px">
            <el-input v-model="userForm.problem" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案" label-width="120px">
            <el-input v-model="userForm.answer" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="userFormVisible = false;userForm={};">取 消</el-button>
          <el-button type="primary" @click="updateUserInfo">确 定</el-button>
        </div>
      </el-dialog>


    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import {deleteUser, updateUser} from "@/api/user";

//完成个人信息查看部分
// 完成修改个人信息部分
// 完成删除用户部分（需要输入问题答案才能进行删除）
export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data() {
    return {
      // 删除用户验证
      inputAnswerTableVisible: false,
      inputAnswer: '',
      // 用户详细信息
      userInfoVisible: false,
      inputAnswer2: '',
      userFormVisible: false,
      userForm: {
        id: '',
        username: '',
        password: '',
        problem: '',
        answer: ''
      },
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      // 跳转到登陆页面
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    // 判断删除用户的回答是否正确
    checkAnswer() {
      if (this.inputAnswer === this.$store.state.user.answer) {
        // 密码正确之后删除用户
        this.inputAnswerTableVisible = false;
        if (this.inputAnswer2 === null) {
          // 删除用户验证通过
          deleteUser(this.$store.state.user.id).then(response => {
            this.$message.success("删除成功！");
            this.logout();
          })
        } else {
          // 修改用户详细验证通过
          this.userFormVisible = true;
          this.userForm={};
        }
      } else {
        this.$message.error('回答错误！');
      }
    },
    // 修改用户
    updateUserInfo() {
      this.userForm.id = this.$store.state.user.id;
      this.userForm.username = this.$store.state.user.name;
      updateUser(this.userForm).then(response => {
        this.$message.success('修改成功！');
        this.userFormVisible = false;
        this.userInfoVisible = false;
        this.$store.dispatch('user/getInfo').then();
      })
    }


  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
