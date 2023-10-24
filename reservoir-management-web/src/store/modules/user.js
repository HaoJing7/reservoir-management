import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    password: '',
    id: '',
    problem: '',
    answer: '',
  }
}

const state = getDefaultState()

// 设置state中的值
const mutations = {
  // 注销的时候重新设置状态
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },

  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_ID: (state, id) => {
    state.id = id;
  },
  SET_PASSWORD: (state, password) => {
    state.password = password;
  },
  SET_PROBLEM: (state, problem) => {
    state.problem = problem;
  },
  SET_ANSWER: (state, answer) => {
    state.answer = answer;
  }

}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const { data } = response

        if (!data) {
          reject('登陆失败！')
        }

        console.log(data.userInfo)

        // 拆解出用户信息
        const { id, username, password, problem, answer } = data.userInfo

        commit('SET_NAME', username)
        commit('SET_ID', id)
        commit('SET_PASSWORD', password)
        commit('SET_PROBLEM', problem)
        commit('SET_ANSWER', answer)

        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

