import { axiosClient } from "../axios";

export async function getAllQuestions() {

  try {
    let response = await axiosClient.get("/question");
    return response.data;
  } catch(e) {
    console.error(e);
    throw(e);
  }
}