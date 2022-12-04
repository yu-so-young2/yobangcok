import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/users/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  await api.get(`/users/info/${userid}`).then(success).catch(fail);
}

async function createUser(user, success, fail) {
  await api.post(`/users/regist`, JSON.stringify(user)).then(success).catch(fail);
}

async function updateUser(user, success, fail) {
  await api.put(`/users/user`, JSON.stringify(user)).then(success).catch(fail);
}
async function deleteUser(id, success, fail) {
  console.log(id);
  await api.delete(`/users/user/${id}`).then(success).catch(fail);
}

async function idDuplecateCheck(id, success, fail) {
  console.log(id);
  await api.get(`/users/regist/${id}`).then(success).catch(fail);
}

export { login, findById, createUser, updateUser, deleteUser, idDuplecateCheck };
