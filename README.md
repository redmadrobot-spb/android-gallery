[![Version](https://api.bintray.com/packages/rmrspb/android-maven/android-gallery/images/download.svg)](https://bintray.com/rmrspb/android-maven/android-gallery/_latestVersion)
# Android-gallery
Simple and standard android gallery that allows you to view media content fullscreen one by one including video player for videos.
Android gallery includes all usual gestures like swipe to dismiss, pinch to zoom, quick scaling by double tap and other. 

![](android-gallery.gif)
## Requirements
For proper work you need AndroidX in your project. If you are still using support library, use this [deprecated version of android-gallery](https://github.com/redmadrobot-spb/android-gallery-old).
## Install
Add this in your root build.gradle file: 
```groovy
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
	maven { url "https://dl.bintray.com/rmrspb/android-maven" }
    }
}
```

After that add library to your module:
```groovy
implementation 'com.git.gallery:android-gallery:latest.version.here'
```
## Usage
All you need to create list of your media content is URL of each of image/video. 
Just create list of `Media`:
```kotlin
fun createGallery() {
  val listOfMedia = arrayListOf(
           Media.Video(
                  "video-thumbnail-url",
                 "video-source-url"
           ),
           Media.Image(
                "image-url"
           ),
)
}
```  
Pass created list to `GalleryFragment` with the position of image you want to view. 
After that you can show fragment by passing `FragmentManager` and fragment tag to `show` method:
```kotlin
fun onImageClick(position: Int) {
  GalleryFragment
              .create(listOfMedia, position)
              .show(supportFragmentManager, "fragment_tag_gallery")
}
```
For more information just look throw the sample.
## Used libraries
* [PhotoView](https://github.com/chrisbanes/PhotoView) for images viewing
* [ExoPlayer](https://github.com/google/ExoPlayer) for videos playing
* [Glide](https://github.com/bumptech/glide) for media content loading

## License
```
MIT License

Copyright (c) 2018 Redmadrobot

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
