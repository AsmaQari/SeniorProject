

import streamlit as st 
pip install diffusers==0.10.2 transformers scipy ftfy accelerate
import torch
from diffusers import StableDiffusionPipeline



pipe = StableDiffusionPipeline.from_pretrained("CompVis/stable-diffusion-v1-4", revision="fp16", torch_dtype=torch.float16)
pipe.to("cuda") 
  
st.title("Creative Thoughts model") 
text=st.text_area("Enter your text ")

def run_model_RealisticPhoto(InputText):
    # realistic 
    prompt = InputText
    prompt+=",realistic, portait"
    image = pipe(prompt).images[0]
    # you can save the image with
    image.save(f"astronaut_rides_horse.png")
    image = pipe(prompt, guidance_scale=7.5, num_inference_steps=200).images[0]

    # you can save the image with
    image.save(f"astronaut_rides_horse1.png")



    image = pipe(prompt, guidance_scale=7.5, num_inference_steps=20).images[0]

    # you can save the image with
    image.save(f"astronaut_rides_horse2.png")



def run_model_ArtsiticPhoto(InputText):
    # artsitic image
    prompt = InputText
    prompt+="national geographic, portrait, photo,cartoon,not real,art,cinematic,cinematic atmosphere, dynamic dramatic cinematic lighting,concept art,digital painting,mystery,fantasy art drawn by disney concept artists"
    image = pipe(prompt).images[0]

    # you can save the image with
    image.save(f"astronaut_rides_horse3.png")
    image = pipe(prompt, guidance_scale=7.5, num_inference_steps=200).images[0]

    # you can save the image with
    image.save(f"astronaut_rides_horse4.png")
    image = pipe(prompt, guidance_scale=7.5, num_inference_steps=20).images[0]

    # you can save the image with
    image.save(f"astronaut_rides_horse5.png")
    
if st.button("Realistic"): 
    run_model_RealisticPhoto(text)
    
    
if st.button("Artisitic"):
    run_model_ArtsiticPhoto(text)    