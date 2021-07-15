import Cookies from "js-cookie";


export function setPlatform (state, isDesktop) {
  state.isDesktopPlatform = isDesktop
}

export function setMenuActive (state, index) {
	console.log('setMenuActive', state,index)
	const keys = Object.keys(state.menus)
	state.activeMenu = keys[index]
}

export function setMenuActiveMenuName (state, menuName) {
	state.activeMenu = menuName
}

export function SET_ACCESS_TOKEN (state, user) {
	console.log("SET_ACCESS_TOKEN: "+ user);
  state.accessToken = Cookies.get("accessToken");
  if(user){
    (state.user=user), (state.userId=user.userid),(state.userName=user.username);
  }
}

export function LOGIN (state, payload) {
	Cookies.set("accessToken",payload["auth-token"]);
  state.accessToken=payload["auth-token"];
  state.userId=payload["user-id"];
  state.userName=payload["user-name"];
  state.user=payload["user"];
}

export function LOGOUT (state) {
	Cookies.remove("accessToken");
  state.accessToken=null;
  state.userId="";
  state.userName="";
}

export function SET_USER (state, user) {
	state.user=user;
}
